
    create table MemberRichFacesKitchensink (
        id bigint not null auto_increment,
        email varchar(255),
        name varchar(255),
        phone_number varchar(255),
        primary key (id),
        unique (email)
    );

    create table categorias_productos (
        idcategorias_productos integer not null,
        nombre varchar(255),
        primary key (idcategorias_productos)
    );

    create table menus (
        idmenus integer not null,
        estatus integer,
        nombre varchar(255),
        sede_rif varchar(255),
        primary key (idmenus)
    );

    create table menus_elementos (
        idmenus_elementos integer not null,
        descripcion varchar(255),
        estatus integer,
        nombre varchar(255),
        precio float,
        unidades integer,
        menu_idmenus integer,
        primary key (idmenus_elementos)
    );

    create table productos (
        idproductos integer not null,
        descripcion varchar(255),
        nombre varchar(255),
        precio float,
        categoria_idcategorias_productos integer,
        tipo_idtipos_productos integer,
        primary key (idproductos)
    );

    create table restaurantes (
        rif varchar(255) not null,
        direccionfiscal varchar(255),
        razonsocial varchar(255),
        primary key (rif)
    );

    create table restos.usuarios (
        cedula varchar(255) not null,
        apellido varchar(255),
        direccion varchar(255),
        email varchar(255),
        login varchar(255),
        nombre varchar(255),
        clave varchar(255),
        telefono varchar(255),
        tipo integer,
        primary key (cedula)
    );

    create table sedes (
        rif varchar(255) not null,
        direccionFisica varchar(255),
        email varchar(255),
        nombre varchar(255),
        telefono varchar(255),
        restaurante_rif varchar(255),
        primary key (rif)
    );

    create table tipos_productos (
        idtipos_productos integer not null,
        nombre varchar(255),
        primary key (idtipos_productos)
    );

    alter table menus 
        add index FK62F96F4FB7A8743 (sede_rif), 
        add constraint FK62F96F4FB7A8743 
        foreign key (sede_rif) 
        references sedes (rif);

    alter table menus_elementos 
        add index FKD9FECE55719FE905 (menu_idmenus), 
        add constraint FKD9FECE55719FE905 
        foreign key (menu_idmenus) 
        references menus (idmenus);

    alter table productos 
        add index FKC14E5B13BA97734E (tipo_idtipos_productos), 
        add constraint FKC14E5B13BA97734E 
        foreign key (tipo_idtipos_productos) 
        references tipos_productos (idtipos_productos);

    alter table productos 
        add index FKC14E5B1385DAFE61 (categoria_idcategorias_productos), 
        add constraint FKC14E5B1385DAFE61 
        foreign key (categoria_idcategorias_productos) 
        references categorias_productos (idcategorias_productos);

    alter table sedes 
        add index FK683FC80D003A973 (restaurante_rif), 
        add constraint FK683FC80D003A973 
        foreign key (restaurante_rif) 
        references restaurantes (rif);
