
package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration;
import org.springframework.ai.model.google.genai.autoconfigure.chat.GoogleGenAiChatAutoConfiguration;
import org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

@EnabledIfEnvironmentVariable(named = "GOOGLE_API_KEY", matches = ".+")
class GoogleGenAiChatClientIT {

    @Test
    void should_answerSimpleMath_when_prompted() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(
                        ToolCallingAutoConfiguration.class,
                        GoogleGenAiChatAutoConfiguration.class,
                        ChatClientAutoConfiguration.class))
                .withPropertyValues(
                        "spring.ai.google.genai.api-key=${GOOGLE_API_KEY}",
                        "spring.ai.google.genai.chat.options.model=gemini-3.5-flash-lite")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).hasSingleBean(ChatClient.Builder.class);

                    ChatClient chatClient = context.getBean(ChatClient.Builder.class)
                            .defaultSystem(
                                    "Você é um matemático. Responda apenas com o resultado.")
                            .build();

                    String response = chatClient.prompt(
                            "Quanto é 10 mais 20, menos 30? Responda apenas com o resultado.").call().content();

                    assertThat(response).isNotBlank();
                    assertThat(response).contains("0");

                    System.out.println("Resposta do Gemini: " + response);
                });
    }
}
