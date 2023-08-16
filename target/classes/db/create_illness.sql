--liquibase formatted sql
--changeset mo.belyaev:1.0.0

create table if not exists illness(

    ill_med_name text primary key,
    ill_common_name text

);