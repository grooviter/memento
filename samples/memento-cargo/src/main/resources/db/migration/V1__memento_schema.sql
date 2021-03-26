CREATE TABLE IF NOT EXISTS memento (
 id UUID PRIMARY KEY,
 aggregate_id UUID,
 type varchar(255),
 version integer,
 json jsonb,
 created_at timestamp NOT NULL,
 UNIQUE (aggregate_id, version)
);

--- UPDATES ARE NOT ALLOWED
--- REVOKE ALL ON TABLE memento FROM public;
--- GRANT SELECT, INSERT ON TABLE memento TO public;

CREATE TABLE IF NOT EXISTS memento_snapshot (
 id UUID PRIMARY KEY,
 aggregate_id UUID,
 aggregate_type varchar(255),
 version integer,
 json jsonb,
 created_at timestamp NOT NULL
);

--- UPDATES ARE NOT ALLOWED
--- REVOKE ALL ON TABLE memento_snapshot FROM public;
--- GRANT SELECT, INSERT ON TABLE memento_snapshot TO public;