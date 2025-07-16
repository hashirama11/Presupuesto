# Arquitectura de Registro de Cambios
### Esta qruitectura es analoga a IngresoModel
    Esta arquitectura permite registrar cada cambio de cada instancia de GastoModel.
    La tabla que registra estos cambios es GastoUpdate - Que usa GastoUpdateId para persistir el primary key
    de GastoModel y crear una relacion de Uno a Muchos.
![Arquitectura de registro de Cambios](\src\static\arquitectura_gasto_model.svg)

## Flujo de GastoModel
    GastoModel: Entidad principal que representa un gasto actual

    GastoUpdate: Entidad que almacena versiones históricas de gastos

    GastoUpdateId: Clave compuesta que vincula cada versión con su gasto original
    
    Solo se crea un registro en GastoModel. GastoUpdate no interviene.

GastoUpdate no interviene
![Flujo de GastoModel](\src\static\flujo_gasto_model.svg)

## Flujo de Actualizacion
![Flujo de GastoUpdate](\src\static\flujo_gasto_model_update.svg)