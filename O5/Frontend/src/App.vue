<template>
  <div class="container has-text-centered mt-3">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link :to="{ name: 'contact' }">Contact</router-link>
    </div>
  </div>
  <router-view />
</template>
