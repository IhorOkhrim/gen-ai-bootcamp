package com.epam.training.gen.ai.config;

import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class SemanticKernelConfiguration {

    /**
     * Creates and returns a new instance of {@link ChatHistory} with session scope.
     * <p>
     * This method is annotated with {@code @Bean}, indicating that it produces a bean to be managed by the Spring
     * container. The {@code @Scope} annotation specifies that the bean has session scope, meaning that a new instance
     * of {@link ChatHistory} will be created for each HTTP session. The {@code proxyMode} is set to
     * {@code ScopedProxyMode.TARGET_CLASS}, which means that a CGLIB-based proxy will be created for the bean, allowing
     * it to be injected into other beans while preserving the session scope.
     * </p>
     *
     * @return a new instance of {@link ChatHistory} with session scope.
     */
    @Bean
    @Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ChatHistory chatHistory() {
        return new ChatHistory();
    }
}
