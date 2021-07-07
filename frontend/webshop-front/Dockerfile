FROM node:lts-alpine
WORKDIR /webshop-front
COPY package*.json ./
RUN  npm install
COPY . .
EXPOSE 4200
CMD ["npm", "start"]