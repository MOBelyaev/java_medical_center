--liquibase formatted sql
--changeset mo.belyaev:1.0.0

create table if not exists patient(

    pat_id UUID primary key,
    pat_first_name text,
    pat_last_name text

);