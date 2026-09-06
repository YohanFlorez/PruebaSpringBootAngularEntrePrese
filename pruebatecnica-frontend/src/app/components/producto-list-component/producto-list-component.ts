import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ProductoService } from '../../services/producto.service';
import { Producto } from '../../models/producto';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from '../../services/notification.service';
import { CATEGORIAS } from '../../shared/constants/categorias';
@Component({
  imports: [CommonModule, FormsModule],
  selector: 'app-producto-list-component',
  styleUrl: './producto-list-component.css',
  templateUrl: './producto-list-component.html',
})
export class ProductoListComponent implements OnInit {
    categorias = CATEGORIAS;
  productos: Producto[] = [];
  categoriaFiltro: string = '';
  cargando = false;
  error: string | null = null;

  constructor(
    private productoService: ProductoService,
    private router: Router,
    private notificacion: NotificationService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.cargando = true;
    this.productoService.listar(this.categoriaFiltro).subscribe({
      next: (data) => {
        this.productos = data;
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.error = 'Error al cargar productos';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  nuevoProducto(): void {
    this.router.navigate(['/productos/nuevo']);
  }

  editarProducto(id: number): void {
    this.router.navigate(['/productos/editar', id]);
  }

  async eliminar(id: number): Promise<void> {
    const confirmado = await this.notificacion.confirmar({
      titulo: '¿Eliminar producto?',
      mensaje: 'Esta acción no se puede deshacer.',
      textoConfirmar: 'Eliminar',
    });

    if (!confirmado) return;

    this.productoService.eliminar(id).subscribe({
      next: () => {
        this.productos = this.productos.filter(p => p.id !== id);
        this.cdr.detectChanges();
        this.notificacion.exito({
          titulo: 'Eliminado',
          mensaje: 'El producto se eliminó correctamente.',
        });
      },
      error: () => {
        this.notificacion.error({
          titulo: 'Error',
          mensaje: 'No se pudo eliminar el producto.',
        });
      }
    });
  }
}
