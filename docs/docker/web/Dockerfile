# docker build ricky/nginx:latest .
FROM nginx:stable

LABEL maintainer="NGINX Docker Maintainers <809155736@qq.com>"

ENV LANG C.UTF-8
ENV DEBIAN_FRONTEND=noninteractive

# modify group name
RUN groupmod -n nobody nogroup
COPY ./nginx.conf /etc/nginx/nginx.conf
RUN sed -i "s/user  www-data/user  nobody/" /etc/nginx/nginx.conf

WORKDIR /etc/nginx/conf.d

EXPOSE 80

ENTRYPOINT ["nginx"]
CMD ["-g", "daemon off;"]
