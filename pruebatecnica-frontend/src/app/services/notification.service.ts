export interface ConfirmOptions {
  titulo: string;
  mensaje: string;
  textoConfirmar?: string;
  textoCancelar?: string;
}

export interface MensajeOptions {
  titulo: string;
  mensaje: string;
}

/**
 * Contrato para mostrar notificaciones al usuario.
 * Los componentes dependen de esta clase abstracta, nunca
 * de la librería concreta que la implementa (SweetAlert2, Angular Material, etc.).
 */
export abstract class NotificationService {
  abstract confirmar(opciones: ConfirmOptions): Promise<boolean>;
  abstract exito(opciones: MensajeOptions): void;
  abstract error(opciones: MensajeOptions): void;
}
