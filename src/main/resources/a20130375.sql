create database a20130375;

create table elementos
(
    id_element     int auto_increment
        primary key,
    id_variable    varchar(255) null,
    clase          varchar(255) null,
    tipo           varchar(255) null,
    ambito         int          null,
    tam_arreglo    varchar(255) null,
    dim_arreglo    int          null,
    cant_parametro int          null,
    tipo_parametro varchar(45)  null
);


