package utp.edu.codekion.finanzas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios_categoria")
public class UsuariosCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_categoria_id_gen")
    @SequenceGenerator(name = "usuarios_categoria_id_gen", sequenceName = "usuarios_categoria_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_tra")
    private TipoTransaccion idTipoTra;

    @ManyToOne
    @JoinColumn(name = "id_tipo_cat")
    private Categoria idTipoCat;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Size(max = 50)
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

}