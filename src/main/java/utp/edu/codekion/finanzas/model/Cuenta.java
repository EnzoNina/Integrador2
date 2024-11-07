package utp.edu.codekion.finanzas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "cuentas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    @Id
    @ColumnDefault("nextval('cuentas_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "banco", nullable = false)
    private Banco banco;

    @Size(max = 50)
    @NotNull
    @Column(name = "num_cuenta", nullable = false, length = 50)
    private String numCuenta;

    @Size(max = 50)
    @NotNull
    @Column(name = "num_cuenta_interbancario", nullable = false, length = 50)
    private String numCuentaInterbancario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;

    @Size(max = 16)
    @Column(name = "num_tarjeta", length = 16)
    private String numTarjeta;

    @ManyToOne
    @JoinColumn(name = "tipo_tarjeta")
    private TipoTarjeta tipoTarjeta;

}