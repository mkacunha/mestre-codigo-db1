package com.mkacunha.processadorcep.domain.cep.cepfile;

import java.util.List;

import static java.util.UUID.randomUUID;

public class CepFile {

	private final String session;

	private final String token;

	private String name;

	private List<String> ceps;

	public CepFile(String session, String name, List<String> ceps) {
		this.session = session;
		this.token = randomUUID().toString();
		this.name = name;
		this.ceps = ceps;
	}

	public String getSession() {
		return session;
	}

	public String getToken() {
		return token;
	}

	public String getName() {
		return name;
	}

	public List<String> getCeps() {
		return ceps;
	}

	public int size() {
		return this.ceps.size();
	}
}
