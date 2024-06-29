DELETE FROM account;

INSERT INTO account
(
  username,
  password,
  first_name,
  last_name
)
VALUES
(
  'demo',
  '$2a$10$jFvyAAcsi7DGFidFRxWO3uFw5r2vnacdFGWyQiy0juoY6Hv.gjlNu',
  'Taro',
  'Yamada'
);
