DELETE FROM account;

-- サンプルユーザー1  ユーザー名:demo パスワード: demo
-- サンプルユーザー2  ユーザー名:demo2 パスワード: demo2
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
),
(
  'demo2',
  '$2a$10$VFMR8k1Fzq.YhtaSzP.6NOSxgyL1KppgpeNgp60Bw3aZhHozs0zRO',
  'Jiro',
  'Yamada'
);
