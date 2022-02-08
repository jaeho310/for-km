FROM node as build-stage
WORKDIR /app
COPY . .

RUN yarn install -g
RUN yarn run build

# # production stage
FROM nginx:stable-alpine as production-stage
COPY  ./nginx/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 8086
CMD ["nginx", "-g", "daemon off;"]