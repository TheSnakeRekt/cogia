FROM node:14-alpine
WORKDIR /app
COPY . .
RUN npm install
RUN npm install
EXPOSE 3000

RUN chown -R node /app
USER node
CMD ["npm", "start"]
