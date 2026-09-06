import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';
import { ConfirmOptions, MensajeOptions, NotificationService } from './notification.service';

const COLOR_PRIMARIO = '#1F6F5C';
const COLOR_PELIGRO = '#B3402E';
const COLOR_NEUTRO = '#4A524E';

@Injectable()
export class SweetalertNotificationService extends NotificationService {

  async confirmar(opciones: ConfirmOptions): Promise<boolean> {
    const resultado = await Swal.fire({
      title: opciones.titulo,
      text: opciones.mensaje,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: opciones.textoConfirmar ?? 'Confirmar',
      cancelButtonText: opciones.textoCancelar ?? 'Cancelar',
      confirmButtonColor: COLOR_PELIGRO,
      cancelButtonColor: COLOR_NEUTRO,
    });

    return resultado.isConfirmed;
  }

  exito(opciones: MensajeOptions): void {
    Swal.fire({
      title: opciones.titulo,
      text: opciones.mensaje,
      icon: 'success',
      confirmButtonColor: COLOR_PRIMARIO,
    });
  }

  error(opciones: MensajeOptions): void {
    Swal.fire({
      title: opciones.titulo,
      text: opciones.mensaje,
      icon: 'error',
      confirmButtonColor: COLOR_PRIMARIO,
    });
  }
}
