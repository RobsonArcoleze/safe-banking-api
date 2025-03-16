package br.com.tokiomarine.safebankingapi.infrastructure.config;

import br.com.tokiomarine.safebankingapi.application.usecase.TransferScheduleUseCase;
import br.com.tokiomarine.safebankingapi.application.usecase.VisualizeExtractUseCase;
import br.com.tokiomarine.safebankingapi.application.usecase.impl.TransferScheduleUseCaseImpl;
import br.com.tokiomarine.safebankingapi.application.usecase.impl.VisualizeExtractUseCaseImpl;
import br.com.tokiomarine.safebankingapi.domain.repository.TransferRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private final TransferRepository transferRepository;

    public AppConfig(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Bean
    public TransferScheduleUseCase transferScheduleUseCase() {
        return new TransferScheduleUseCaseImpl(transferRepository);
    }

    @Bean
    public VisualizeExtractUseCase visualizeExtractUseCase() {
        return new VisualizeExtractUseCaseImpl(transferRepository);
    }
}
