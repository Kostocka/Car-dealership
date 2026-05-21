create table public.configured_car_order
(
    deleted             boolean not null,
    created_at          timestamp(6) with time zone,
    updated_at          timestamp(6) with time zone,
    body_id             uuid,
    client_id           uuid,
    engine_id           uuid,
    gearbox_id          uuid,
    id                  uuid    not null
        primary key,
    interior_id         uuid,
    manager_id          uuid,
    wheels_id           uuid,
    brand               varchar(255),
    cancellation_reason varchar(255),
    color               varchar(255),
    drivetrain_type     varchar(255)
        constraint configured_car_order_drivetrain_type_check

            check ((drivetrain_type)::text = ANY
        ((ARRAY ['RWD'::character varying, 'FWD'::character varying, 'FourWD'::character varying, 'AWD'::character varying])::text[])),
    model               varchar(255),
    state               varchar(255)
        constraint configured_car_order_state_check
            check ((state)::text = ANY
                   ((ARRAY ['Created'::character varying, 'Paid'::character varying, 'Approved'::character varying, 'ReadyForPick'::character varying, 'Cancelled'::character varying, 'Finished'::character varying])::text[]))
);

alter table public.configured_car_order
    owner to "user";

create table public.outbox_events
(
    processed      boolean not null,
    created_at     timestamp(6) with time zone,
    aggregate_id   uuid,
    id             uuid    not null
        primary key,
    aggregate_type varchar(255),
    event_type     varchar(255),
    payload        oid
);

alter table public.outbox_events
    owner to "user";

create table public.stock_car_order
(
    deleted             boolean not null,
    created_at          timestamp(6) with time zone,
    updated_at          timestamp(6) with time zone,
    car_id              uuid,
    client_id           uuid,
    id                  uuid    not null
        primary key,
    manager_id          uuid,
    cancellation_reason varchar(255),
    order_state         varchar(255)
        constraint stock_car_order_order_state_check
            check ((order_state)::text = ANY
        ((ARRAY ['Created'::character varying, 'Approved'::character varying, 'Paid'::character varying, 'Cancelled'::character varying, 'Finished'::character varying, 'ReadyForPickup'::character varying])::text[]))
    );

alter table public.stock_car_order
    owner to "user";

create table public.test_drive
(
    start_time timestamp(6),
    car_id     uuid,
    client_id  uuid,
    id         uuid not null
        primary key
);

alter table public.test_drive
    owner to "user";

create table public.test_drive_cars
(
    is_available boolean,
    car_id       uuid not null
        primary key
);

alter table public.test_drive_cars
    owner to "user";

