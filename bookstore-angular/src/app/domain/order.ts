import {OrderItem} from './orderItem';
import {User} from './user';

export class Order {
  id: number;
  deliveryDate: string;
  price: number;
  items: OrderItem[];
  user: User;
}
