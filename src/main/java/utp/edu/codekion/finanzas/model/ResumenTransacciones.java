package utp.edu.codekion.finanzas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "resumen_transacciones")
public class ResumenTransacciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('resumen_transacciones_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @Size(max = 50)
    @NotNull
    @Column(name = "periodo", nullable = false, length = 50)
    private String periodo;

    @NotNull
    @Column(name = "transacciones", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> transacciones;

    @NotNull
    @Column(name = "total_ingresos", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalIngresos;

    @NotNull
    @Column(name = "total_egresos", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalEgresos;

    @NotNull
    @Column(name = "total_ahorro", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAhorro;

    @NotNull
    @Column(name = "total_deuda", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalDeuda;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

}