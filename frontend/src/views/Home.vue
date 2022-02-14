<template>
  <v-card max-width="700" class="mx-auto mt-15">
    <v-card-title>
      <div class="font-weight-bold text-h4 black--text">
        개발환경
      </div>
    </v-card-title>
    <v-card-text class="mt-2 font-weight-bold">
      <ul>
        <li>back-end
          <ul>
            <li>java 11</li>
            <li>spring-boot 2.5.6</li>
            <li>junit5(jupiter), mockito, assertj</li>
            <li>maven</li>
            <li>h2</li>
          </ul>
        </li>
        <li>front-end
          <ul>
            <li>vue2</li>
            <li>vuex</li>
            <li>yarn</li>
          </ul>
        </li>
        <li>dockerizing
          <ul>
            <li>build image: maven:3.8-jdk-11</li>
            <li>runtime image: openjdk:11-jdk-slim</li>
          </ul>
        </li>
      </ul>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "home"
}
</script>