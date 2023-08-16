--liquibase formatted sql
--changeset mo.belyaev:1.0.0

create table if not exists physician (

    phy_id UUID primary key,
    phy_title text,
    phy_first_name text,
    phy_last_name text

);