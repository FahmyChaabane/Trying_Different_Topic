DROP TABLE IF EXISTS operation;
CREATE TABLE operation(
  id      SERIAL PRIMARY KEY,
  asset   VARCHAR(50),
  amount  INT
);
