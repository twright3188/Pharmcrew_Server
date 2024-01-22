package kr.ant.kpa.pharmcrew.config.storage;

import kr.ant.kpa.pharmcrew.config.storage.impl.LocalStorage;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class StorageConfig {
    public static enum STORAGE {
        LOCAL,
    }

    @Bean
    @Conditional(LocalCondition.class)
    public Storage storage() {
        return new LocalStorage();
    }

    public static class LocalCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }
}
