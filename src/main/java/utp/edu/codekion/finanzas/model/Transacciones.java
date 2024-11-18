package utp.edu.codekion.finanzas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "transacciones")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transacciones {
    @Id
    @ColumnDefault("nextval('transacciones_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private UsuariosCategoria idCategoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_concepto", nullable = false)
    private TipoConcepto idConcepto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_frecuencia", nullable = false)
    private Frecuencia idFrecuencia;

    @NotNull
    @Column(name = "monto", nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "divisa", nullable = false)
    private Divisa divisa;

    @Size(max = 250)
    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "fecha_transaccion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaTransaccion;

    @PrePersist
    public void prePersist() {
        if (fechaTransaccion == null) {
            fechaTransaccion = LocalDate.now();
        }
    }

}