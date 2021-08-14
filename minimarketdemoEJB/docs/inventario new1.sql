/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     17/3/2021 3:19:01                            */
/*==============================================================*/


drop table inv_ajuste_producto;

drop table inv_detalle_ajuste_documento;

drop table inv_detalle_ajuste_producto;

drop table inv_producto;

/*==============================================================*/
/* Table: inv_ajuste_producto                                   */
/*==============================================================*/
create table inv_ajuste_producto (
   id_ajuste            serial               not null,
   motivo               varchar(500)         null,
   tipo_ajuste          varchar(1)           null,
   fecha                date                 null,
   numero_ajuste        varchar(50)          null,
   impreso              bool                 null,
   constraint pk_inv_ajuste_producto primary key (id_ajuste)
);

/*==============================================================*/
/* Table: inv_detalle_ajuste_documento                          */
/*==============================================================*/
create table inv_detalle_ajuste_documento (
   id_detalle_ajuste_documento serial               not null,
   id_ajuste            int4                 null,
   fecha_impresion      date                 null,
   constraint pk_inv_detalle_ajuste_document primary key (id_detalle_ajuste_documento)
);

/*==============================================================*/
/* Table: inv_detalle_ajuste_producto                           */
/*==============================================================*/
create table inv_detalle_ajuste_producto (
   id_detalle_ajuste_producto serial               not null,
   id_ajuste            int4                 null,
   id_producto          int4                 null,
   descripcion          varchar(100)         null,
   constraint pk_inv_detalle_ajuste_producto primary key (id_detalle_ajuste_producto)
);

/*==============================================================*/
/* Table: inv_producto                                          */
/*==============================================================*/
create table inv_producto (
   id_producto          serial               not null,
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

alter table inv_detalle_ajuste_documento
   add constraint fk_inv_deta_reference_inv_ajus foreign key (id_ajuste)
      references inv_ajuste_producto (id_ajuste)
      on delete restrict on update restrict;

alter table inv_detalle_ajuste_producto
   add constraint fk_inv_deta_reference_inv_prod foreign key (id_producto)
      references inv_producto (id_producto)
      on delete restrict on update restrict;

alter table inv_detalle_ajuste_producto
   add constraint fk_inv_deta_reference_inv_ajus foreign key (id_ajuste)
      references inv_ajuste_producto (id_ajuste)
      on delete restrict on update restrict;


	
	
alter table inv_categoria_producto
	add codigo_prod varchar(10);

ALTER TABLE inv_detalle_ajuste_documento  ALTER COLUMN fecha_impresion TYPE timestamp USING CAST(fecha_impresion AS date);
