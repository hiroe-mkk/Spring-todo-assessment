# Spring Boot + Spring Security アプリケーションのセキュリティ診断用プロジェクト

## 概要

Spring Boot + Spring Security アプリケーションでのセキュリティ機能の検証を目的とした学習用サンプルコードです。

**注意**: このコードは学習および検証目的で作成されています。

## 参考文献

- [Macchinetta Server Framework (1.x) Development Guideline](https://macchinetta.github.io/server-guideline/current/ja/)

## 免責事項

本コードの内容の正確性、使用目的への適合性の保証、使用結果についての的確性や信頼性の保証、及び瑕疵担保義務も含め、直接、間接に被ったいかなる損害に対しても一切の責任を負いません。

# 起動方法

## 1. Docker イメージのビルド

```
$ ./gradlew bootBuildImage
```

※ あらかじめ Docker デーモンを起動してから実行してください。

※ 初回は数分かかる可能性があります。

## 2. Docker コンテナの起動とアプリケーションの起動

```
$ docker-compose up --build
```

このコマンドにより、Docker コンテナが起動し、アプリケーションが自動的に実行されます。

コンテナが正常に起動したら、ブラウザで http://localhost:8080 にアクセスして確認できます。
