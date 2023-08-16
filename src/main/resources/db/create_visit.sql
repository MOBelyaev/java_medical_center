--liquibase formatted sql
--changeset mo.belyaev:1.0.0

create table if not exists visit(

    vis_id uuid,
    date_visit timestamp,
    time_visit timestamp,
    pat_id UUID not null,
    phy_id UUID not null,


    primary key (vis_id),
    foreign key (pat_id) references patient(pat_id),
    foreign key (phy_id) references physician(phy_id)
);

