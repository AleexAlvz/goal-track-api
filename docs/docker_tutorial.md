# 🐳 Como rodar a aplicação em Docker

## Como fazer o build da imagem Docker

1. Certifique-se de ter o Docker instalado e em execução no seu sistema.
2. Navegue até o diretório raiz do projeto onde o `Dockerfile` está localizado.
3. Execute o seguinte comando para construir a imagem Docker:

```bash
docker build -t goal-track-api .
```

4. Caso deseje fazer o deploy, lembre-se de adicionar o versionamento da imagem, por exemplo:

```bash
docker build -t goal-track-api:1.0 .
```

5. Caso vá rodar em alguma máquina remota, pode também fazer o build para a arquitetura correta, por exemplo:

```bash
docker build --platform linux/amd64 -t goal-track-api:1.0 .
```

## Como rodar a aplicação em um container

1. Após construir a imagem, execute o seguinte comando para iniciar um container:

```bash
docker run -d -p 8080:8080 --name goal-track-api-container goal-track-api
```

2. A aplicação estará disponível em `http://localhost:8080`.
3. O nome do container será definido pela flag `--name`, neste caso `goal-track-api-container`. Você pode escolher outro
   nome se desejar.
4. O comando `-d` executa o container em segundo plano (detached mode). Caso queira rodar em primeiro plano para ver os
   logs diretamente, basta remover a flag `-d`.
5. `-p 8080:8080` mapeia a porta 8080 do container para a porta 8080 do host, permitindo o acesso à aplicação pela
   maquina que hospeda o container.
6. Para acessar os logs do container, use:

```bash
docker logs -f goal-track-api-container
```

7. Para parar o container, use:

```bash
docker stop goal-track-api-container
```

8. Para remover o container, use:

```bash
docker rm goal-track-api-container
```

9. Para remover a imagem, use:

```bash
docker rmi goal-track-api
```

## Dicas adicionais

1. Consultar container em execução:

```bash
docker ps
```

2. Consultar todos os containers (incluindo os parados):

```bash
docker ps -a
```

3. Consultar imagens disponíveis:

```bash
docker images
```

4. Para rodar o container com variáveis de ambiente (caso queira configurar algo específico), use a flag `-e`:

```bash
docker run -d -p 8080:8080 --name goal-track-api-container -e SPRING_PROFILES_ACTIVE=prod goal-track-api
```

5. Acessar o terminal do container para depuração:

```bash
docker exec -it goal-track-api-container /bin/bash
```

6. Remover todos os containers parados:

```bash
docker container prune
```

7. Remover todas as imagens não utilizadas:

```bash
docker image prune -a
```

8. Inspecionar detalhes do container:

```bash
docker inspect goal-track-api-container
```

9. Verificar o status do container:

```bash
docker stats goal-track-api-container
```

10. Parar todos os containers em execução:

```bash
docker stop $(docker ps -q)
```

11. Remover todos os containers:

```bash
docker rm $(docker ps -a -q)
```

12. Remover todas as imagens:

```bash
docker rmi $(docker images -q)
```
