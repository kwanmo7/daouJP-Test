import { createRouter, createWebHistory } from 'vue-router';
import List from '@/components/List.vue';
import Subscriber from '@/components/Subscriber.vue';
import Dropouts from '@/components/Dropouts.vue';
import Paymentamount from '@/components/Paymentamount.vue';
import Amountused from '@/components/Amountused.vue';
import Salesamount from '@/components/Salesamount.vue';

const routes = [
  {
    path: '/lists',
    name: 'lists',
    component: List
  },
  {
    path: '/subscribers',
    name: 'subscribers',
    component: Subscriber
  },
  {
    path: '/dropouts',
    name: 'dropouts',
    component: Dropouts
  },
  {
    path: '/paymentAmount',
    name: 'paymentAmount',
    component: Paymentamount
  },
  {
    path: '/amountUsed',
    name: 'amountUsed',
    component: Amountused
  },
  {
    path: '/salesAmount',
    name: 'salesAmount',
    component: Salesamount
  }
  
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
