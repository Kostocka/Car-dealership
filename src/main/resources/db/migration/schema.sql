create table body
(
    deleted boolean not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    id uuid not null
        primary key,
    body_type varchar(255)
        constraint body_body_type_check
            check ((body_type)::text = ANY ((ARRAY['CONVERTIBLE'::character varying, 'COUPE'::character varying, 'CUV'::character varying, 'ESTATE'::character varying, 'HATCHBACK'::character varying, 'LIMOUSINE'::character varying, 'TRUCK'::character varying, 'MINIVAN'::character varying, 'PICKUP'::character varying, 'ROADSTER'::character varying, 'SEDAN'::character varying, 'SUV'::character varying, 'VAN'::character varying])::text[]))
    );

alter table body owner to "user";

create table engine
(
    deleted boolean not null,
    power integer not null,
    volume integer not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    id uuid not null
        primary key,
    fuel_type varchar(255)
        constraint engine_fuel_type_check
            check ((fuel_type)::text = ANY ((ARRAY['GASOLINE'::character varying, 'DIESEL'::character varying, 'BIODIESEL'::character varying, 'ETHANOL'::character varying, 'HYDROGEN'::character varying, 'ELECTRIC'::character varying])::text[]))
    );

alter table engine owner to "user";

create table gearbox
(
    deleted boolean not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    id uuid not null
        primary key,
    gear_box_type varchar(255)
        constraint gearbox_gear_box_type_check
            check ((gear_box_type)::text = ANY ((ARRAY['MANUAL'::character varying, 'AUTOMATIC'::character varying])::text[]))
    );

alter table gearbox owner to "user";

create table interior
(
    deleted boolean not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    id uuid not null
        primary key,
    material varchar(255)
);

alter table interior owner to "user";

create table model_part_compatibility
(
    model_id uuid not null,
    part_id uuid not null,
    primary key (model_id, part_id)
);

alter table model_part_compatibility owner to "user";

create table part_compatibility
(
    first_part_id uuid not null,
    second_part_id uuid not null,
    primary key (first_part_id, second_part_id)
);

alter table part_compatibility owner to "user";

create table part_price
(
    price numeric(38,2),
    part_id uuid not null
        primary key
);

alter table part_price owner to "user";

create table part_stock
(
    quantity integer not null,
    part_id uuid not null
        primary key
);

alter table part_stock owner to "user";

create table test_drive
(
    start_time timestamp(6),
    car_id uuid,
    client_id uuid,
    id uuid not null
        primary key
);

alter table test_drive owner to "user";

create table test_drive_cars
(
    is_available boolean,
    car_id uuid not null
        primary key
);

alter table test_drive_cars owner to "user";

create table wheels
(
    deleted boolean not null,
    size integer not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    id uuid not null
        primary key
);

alter table wheels owner to "user";

create table car
(
    deleted boolean not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    body_id uuid
        constraint fkrf5vjaxr3qqub08xdittqa9lj
            references body,
    engine_id uuid
        constraint fknednv54lgu9rfucgemr5eal0j
            references engine,
    gearbox_id uuid
        constraint fk2y1m0j23bbn5b9x7wo3i28ipx
            references gearbox,
    id uuid not null
        primary key,
    interior_id uuid
        constraint fkakffftx0t9dwm5o8lgvhv2whg
            references interior,
    model_id uuid,
    wheels_id uuid
        constraint fktd75j7v5wmo0ea8fhsg8wa48w
            references wheels,
    brand varchar(255),
    color varchar(255),
    drivetrain_type varchar(255)
        constraint car_drivetrain_type_check
            check ((drivetrain_type)::text = ANY ((ARRAY['RWD'::character varying, 'FWD'::character varying, 'FourWD'::character varying, 'AWD'::character varying])::text[])),
	model varchar(255)
);

alter table car owner to "user";

create table car_model
(
    deleted boolean not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    body_id uuid
        constraint fkidbejnhjcct82ynyl9dlkwa2s
            references body,
    engine_id uuid
        constraint fkf671twudqchyiltcyslanx5cw
            references engine,
    gearbox_id uuid
        constraint fkt6ipqd3boeoyaj5ox9oh20dpt
            references gearbox,
    id uuid not null
        primary key,
    interior_id uuid
        constraint fkntj250lldgep6j9qp7ey2tlhq
            references interior,
    wheels_id uuid
        constraint fk8mmu3erxw26hu9uhewha9wn1h
            references wheels,
    brand varchar(255),
    color varchar(255),
    drivetrain_type varchar(255)
        constraint car_model_drivetrain_type_check
            check ((drivetrain_type)::text = ANY ((ARRAY['RWD'::character varying, 'FWD'::character varying, 'FourWD'::character varying, 'AWD'::character varying])::text[])),
	model varchar(255)
);

alter table car_model owner to "user";

create table configured_car_order
(
    deleted boolean not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    body_id uuid
        constraint fkjlhf8p4rct8dsmgao75h3450t
            references body,
    client_id uuid,
    engine_id uuid
        constraint fkdwxb4x3ig15sffj31rb49et35
            references engine,
    gearbox_id uuid
        constraint fkoipk9kpyum2d36lfgnimfuxey
            references gearbox,
    id uuid not null
        primary key,
    interior_id uuid
        constraint fkdtmdc7v4vnsmrd0p3fk8ducf6
            references interior,
    manager_id uuid,
    model_id uuid,
    wheels_id uuid
        constraint fk5f3jlvi3734cme2y12m1tlo6g
            references wheels,
    brand varchar(255),
    color varchar(255),
    drivetrain_type varchar(255)
        constraint configured_car_order_drivetrain_type_check
            check ((drivetrain_type)::text = ANY ((ARRAY['RWD'::character varying, 'FWD'::character varying, 'FourWD'::character varying, 'AWD'::character varying])::text[])),
	model varchar(255),
	state varchar(255)
		constraint configured_car_order_state_check
			check ((state)::text = ANY ((ARRAY['Created'::character varying, 'Paid'::character varying, 'Approved'::character varying, 'ReadyForPick'::character varying, 'Cancelled'::character varying, 'Finished'::character varying])::text[]))
);

alter table configured_car_order owner to "user";

create table stock_car_order
(
    deleted boolean not null,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    car_id uuid
        unique
        constraint fkhy43c9ddhex67ot5kec9mom8v
            references car,
    client_id uuid,
    id uuid not null
        primary key,
    manager_id uuid,
    order_state varchar(255)
        constraint stock_car_order_order_state_check
            check ((order_state)::text = ANY ((ARRAY['Created'::character varying, 'Approved'::character varying, 'Paid'::character varying, 'Cancelled'::character varying, 'Finished'::character varying])::text[]))
    );

alter table stock_car_order owner to "user";