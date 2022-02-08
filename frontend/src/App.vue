<template>
  <layout-index />
</template>

<script>
export default {
  name: 'App',
  components: {
    LayoutIndex: () => import(
        '@/layouts/LayoutIndex'
    ),
  },
};
</script>
