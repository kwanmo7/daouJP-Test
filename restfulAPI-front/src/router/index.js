import { createRouter, createWebHistory } from 'vue-router';
import Record from '@/components/List.vue';

const routes = [
  {
    path: '/lists',
    name: 'lists',
    component: Record
  }
  
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
