package utp.edu.codekion.finanzas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @ColumnDefault("nextval('cuentas_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;

    @Size(max = 16)
    @Column(name = "num_tarjeta", length = 16)
    private String numTarjeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_tarjeta")
    private TipoTarjeta tipoTarjeta;

}