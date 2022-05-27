FROM nginx

#将dist目录内容复制到nginx容器html内部
COPY dist /usr/share/nginx/html/
COPY deploy/nginx.conf /etc/nginx/nginx.conf

EXPOSE 80
