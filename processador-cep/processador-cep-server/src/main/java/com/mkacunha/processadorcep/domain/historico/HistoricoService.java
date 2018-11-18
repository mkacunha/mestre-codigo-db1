package com.mkacunha.processadorcep.domain.historico;

import com.mkacunha.processadorcep.application.controller.historico.HistoricoTranslator;
import com.mkacunha.processadorcep.domain.cep.cepfile.CepFile;
import com.mkacunha.processadorcep.domain.historico.log.HistoricoLog;
import com.mkacunha.processadorcep.domain.historico.log.HistoricoLogRepository;
import com.mkacunha.processadorcep.infrastructure.exception.ServiceException;
import com.mkacunha.processadorcep.infrastructure.exception.TransactionException;
import com.mkacunha.processadorcep.infrastructure.jdbc.Transaction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    public static final String NAO_FOI_POSSIVEL_CRIAR_HISTORICO = "Não foi possível criar um histórico para o processamento.";

    public static final String HISTORICO_NAO_ENCONTRADO = "Histórico de token %s não encontrado.";

    public static final String ERRO_ENCERRAR_PROCESSAMENTO = "Não foi possível encerrar o histórico.";

    public static final String ERRO_BUSCAR_HISTORICO_COM_MAIS_ERROS = "Não foi possível buscar pelo histórico com maior número erros";

    public static final String ERRO_HISTORICOS_NOT_FOUND = "Não possui históricos de processamento para executar a operação";

    private static final Logger LOGGER = Logger.getLogger(HistoricoService.class);

    private final Transaction transaction;

    private final HistoricoRepository repository;

    private final HistoricoLogRepository logRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    private final HistoricoTranslator historicoTranslator;
    @Autowired
    public HistoricoService(Transaction transaction, HistoricoRepository repository,
                            HistoricoLogRepository logRepository, SimpMessageSendingOperations messagingTemplate, HistoricoTranslator historicoTranslator) {
        this.transaction = transaction;
        this.repository = repository;
        this.logRepository = logRepository;
        this.messagingTemplate = messagingTemplate;
        this.historicoTranslator = historicoTranslator;
    }

    public Historico createAndSaveHistorico(String token, String arquivo) {
        try {
            Historico historico = new Historico(token, arquivo);
            transaction.begin();
            repository.insert(historico);
            transaction.commit();
            return historico;
        } catch (TransactionException e) {
            transaction.rollback();
            LOGGER.error(e);
            throw new ServiceException(NAO_FOI_POSSIVEL_CRIAR_HISTORICO);
        }
    }

    public Historico findHistoricoByToken(String token) {
        try {
            transaction.begin();
            return findHistoricoByTokenWithoutTransaction(token);
        } catch (TransactionException e) {
            LOGGER.error(e);
            throw new ServiceException(HISTORICO_NAO_ENCONTRADO);
        } finally {
            transaction.finnaly();
        }
    }

    public void closeProcessing(List<HistoricoLog> logs, CepFile cepFile, Integer registrosNovos,
                                Integer registrosAlterados, Integer registrosComErros) {
        try {
            transaction.begin();
            Historico historico = findHistoricoByTokenWithoutTransaction(cepFile.getToken());
            historico.processado(registrosNovos, registrosAlterados, registrosComErros);
            repository.update(historico);
            saveLogs(historico, logs);
            transaction.commit();
            messagingTemplate.convertAndSendToUser(cepFile.getSession(), "/queue/reply", this.historicoTranslator.apply(historico));
        } catch (TransactionException e) {
            transaction.rollback();
            LOGGER.error(e);
            throw new ServiceException(ERRO_ENCERRAR_PROCESSAMENTO);
        }
    }

    private void saveLogs(Historico historico, List<HistoricoLog> logs) throws TransactionException {
        logs.forEach(log -> log.add(historico));
        logRepository.insert(logs);
    }

    private Historico findHistoricoByTokenWithoutTransaction(String token) throws TransactionException {
        return repository.findByToken(token)
                .orElseThrow(new ServiceException(HISTORICO_NAO_ENCONTRADO, token));
    }
}
