import { createRouter, createWebHistory } from 'vue-router';
import Record from '@/components/List.vue';
import Register from '@/components/Register.vue';

const routes = [
  {
    path: '/lists',
    name: 'lists',
    component: Record
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  }
  
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
