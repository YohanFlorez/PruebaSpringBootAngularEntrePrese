export interface Producto {
  id?: number;          // opcional porque al crear no existe aún
  nombre: string;
  precio: number;
  stock: number;
  categoria: string;
}
