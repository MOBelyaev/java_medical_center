--liquibase formatted sql
--changeset mo.belyaev:1.0.0

create table if not exists diagnose(

    diag_id uuid,
    degree text,
    vis_id uuid,
    ill_med_name text,

    foreign key (vis_id) references visit(vis_id),
    foreign key (ill_med_name) references  illness(ill_med_name)
);