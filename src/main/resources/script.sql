create table PERSONAS (
   CODIGO               SERIAL not null,
   NOMBRES              VARCHAR(25)          not null,
   APELLIDOS            VARCHAR(25)          not null,
   FECHA_NACIMIENTO     DATE                 not null,
   EXPERIENCIA          INT4                 not null,
   constraint PK_PERSONAS primary key (CODIGO)
);