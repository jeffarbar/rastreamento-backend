docker build -t jeffersonfarias/boris-backend:latest  -f Dockerfile .
docker login --username=jeffersonfarias
docker push jeffersonfarias/boris-backend:latest
