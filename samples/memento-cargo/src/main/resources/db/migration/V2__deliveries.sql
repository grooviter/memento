CREATE TABLE IF NOT EXISTS cargo_delivery (
    id UUID PRIMARY KEY,
    delivery_status varchar(255) NOT NULL,
    on_route_since TIMESTAMP,
    requested_at TIMESTAMP NOT NULL,
    delivered_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cargo_participant (
    id UUID PRIMARY KEY,
    name varchar(255) NOT NULL,
    role varchar(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);