package br.com.mkacunha.aspectjdemo.jmx;

import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.*;

@Configuration
@ManagedResource(objectName="bean:name=testBean4", description="My Managed Bean", log=true,
        logFile="jmx.log", currencyTimeLimit=15, persistPolicy="OnUpdate", persistPeriod=200,
        persistLocation="foo", persistName="bar")
public class AnnotationTestBean {

    private static String name;
    private static int age;

    public AnnotationTestBean() {
        System.out.println("===============================> here");
    }

    @ManagedAttribute(description="The Age Attribute", currencyTimeLimit=15)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManagedAttribute(description="The Name Attribute",
            currencyTimeLimit=20,
            defaultValue="bar",
            persistPolicy="OnUpdate")
    public void setName(String name) {
        System.out.println(name);
        this.name = name;
    }

    @ManagedAttribute(defaultValue="foo", persistPeriod=300)
    public String getName() {
        return name;
    }

    @ManagedOperation(description="Add two numbers")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "x", description = "The first number"),
            @ManagedOperationParameter(name = "y", description = "The second number")})
    public int add(int x, int y) {
        return x + y;
    }

    public void dontExposeMe() {
        throw new RuntimeException();
    }

}
