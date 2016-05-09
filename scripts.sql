CREATE DATABASE clojuchips ENCODING = 'UTF8';

CREATE TABLE product
(
  id serial NOT NULL,
  name character varying(70) NOT NULL,
  description character varying(200),
  quantity integer NOT NULL,
  price real NOT NULL,
  CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE promotion
(
  id serial NOT NULL,
  name character varying(80) NOT NULL,
  description character varying(200),
  starting_date timestamp without time zone NOT NULL,
  ending_date timestamp without time zone NOT NULL,
  product_related integer,
  CONSTRAINT promotion_pkey PRIMARY KEY (id),
  CONSTRAINT promotion_product_related_fkey FOREIGN KEY (product_related)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);


