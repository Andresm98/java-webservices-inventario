/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     10/3/2021 2:00:48                            */
/*==============================================================*/


drop table inv_ajuste_producto;

drop table inv_categoria_producto;

drop table inv_historial_ajuste_producto;

drop table inv_producto;

/*==============================================================*/
/* Table: inv_ajuste_producto                                   */
/*==============================================================*/
create table inv_ajuste_producto (
   id_ajuste            serial               not null,
   id_producto          int4                 null,
   motivo               varchar(200)         null,
   tipo_ajuste          varchar(1)           null,
   fecha                date                 null,
   numero_ajuste        varchar(50)          null,
   constraint pk_inv_ajuste_producto primary key (id_ajuste)
);

/*==============================================================*/
/* Table: inv_categoria_producto                                */
/*==============================================================*/
create table inv_categoria_producto (
   id_categoria_producto serial               not null,
   nombre_categoria     varchar(50)          null,
   descripcion_categoria varchar(200)         null,
   constraint pk_inv_categoria_producto primary key (id_categoria_producto)
);

/*==============================================================*/
/* Table: inv_historial_ajuste_producto                         */
/*==============================================================*/
create table inv_historial_ajuste_producto (
   id_ajuste_producto   serial               not null,
   id_ajuste            int4                 null,
   fecha_inicial        date                 null,
   fecha_final          date                 null,
   constraint pk_inv_historial_ajuste_produc primary key (id_ajuste_producto)
);

/*==============================================================*/
/* Table: inv_producto                                          */
/*==============================================================*/
create table inv_producto (
   id_producto          serial               not null,
   id_categoria_producto int4                 null,
   codigo_producto      varchar(50)          null,
   nombre_producto      varchar(100)         null,
   descripcion_         varchar(200)         null,
   iva                  decimal              null,
   costo                decimal(7,2)         null,
   pvp                  decimal(7,2)         null,
   estado               bool                 null,
   stock                int4                 null,
   constraint pk_inv_producto primary key (id_producto)
);

alter table inv_ajuste_producto
   add constraint fk_inv_ajus_reference_inv_prod foreign key (id_producto)
      references inv_producto (id_producto)
      on delete restrict on update restrict;

alter table inv_historial_ajuste_producto
   add constraint fk_inv_hist_reference_inv_ajus foreign key (id_ajuste)
      references inv_ajuste_producto (id_ajuste)
      on delete restrict on update restrict;

alter table inv_producto
   add constraint fk_inv_prod_reference_inv_cate foreign key (id_categoria_producto)
      references inv_categoria_producto (id_categoria_producto)
      on delete restrict on update restrict;

