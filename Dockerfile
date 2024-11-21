# 현 Spring 프로젝트와는 상관없이 nginx 이미지 만들 때 사용하는 파일
# 현 파일로 이미지 만들기 -> docker build -t nginx_new_dockerfile:01 .
FROM nginx:latest
RUN echo '<h1>This is nginx container with image dockerfile</h1>' > /usr/share/nginx/html/index.html
EXPOSE 80