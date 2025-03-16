package br.com.tokiomarine.safebankingapi.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfer")
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_origin_id", referencedColumnName = "id", nullable = false)
    private BankAccountEntity accountOrigin;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_destination_id", referencedColumnName = "id", nullable = false)
    private BankAccountEntity accountDestination;
    private BigDecimal transferValue;
    private LocalDateTime dateSchedule;
    private LocalDateTime dateTransfer;
    private BigDecimal rate;
}
