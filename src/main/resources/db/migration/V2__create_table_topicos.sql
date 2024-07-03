create table topicos(

    id bigint not null auto_increment,
    usuario_id bigint not null,
    mensaje varchar(200) not null,
    nombre_curso varchar(100) not null unique,
    titulo varchar(100) not null unique,

    primary key(id),
    constraint fk_topicos_usuarios_id foreign key(usuario_id) references usuarios(id)

);