import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../services/producto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotificationService } from '../../services/notification.service';
import { CATEGORIAS } from '../../shared/constants/categorias';
@Component({
  imports: [CommonModule, ReactiveFormsModule],
  selector: 'app-producto-form-component',
  styleUrl: './producto-form-component.css',
  templateUrl: './producto-form-component.html',
})
export class ProductoFormComponent implements OnInit {
   categorias = CATEGORIAS;
  form: FormGroup;
  id: number | null = null;

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService,
    private route: ActivatedRoute,
    private router: Router,
    private notificacion: NotificationService,
  ) {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      precio: [0, [Validators.required, Validators.min(0.01)]],
      stock: [0, Validators.required],
      categoria: ['']
    });
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.id = +idParam;
      this.productoService.obtenerPorId(this.id).subscribe(producto => {
        this.form.patchValue(producto);
      });
    }
  }

  guardar(): void {
    if (this.form.invalid) return;

    const producto = this.form.value;
    const esEdicion = this.id !== null;
    const peticion = esEdicion
      ? this.productoService.actualizar(this.id!, producto)
      : this.productoService.crear(producto);

    peticion.subscribe({
      next: () => {
        this.notificacion.exito({
          titulo: esEdicion ? 'Producto actualizado' : 'Producto creado',
          mensaje: esEdicion
            ? 'Los cambios se guardaron correctamente.'
            : 'El producto se creó correctamente.',
        });
        this.router.navigate(['/productos']);
      },
      error: () => {
        this.notificacion.error({
          titulo: 'Error',
          mensaje: esEdicion
            ? 'No se pudo actualizar el producto.'
            : 'No se pudo crear el producto.',
        });
      }
    });
  }
    cancelar(): void {
  this.router.navigate(['/productos']);
}

}



