DTO Para enviar ao google calendar no formato json

{
    "id": string,
    "summary": string,
    "description": string,
    "location": string,
    "colorId": string -> fica salvo em algum lugar das configurações do adapter,
    "start": {
        "dateTime": datetime -> vem com a mensagem da fila,
        "timeZone": string -> fica salvo em algum lugar das configurações do adapter
    },
    "end": {
        "dateTime": datetime -> vem com a mensagem da fila,
        "timeZone": string -> fica salvo em algum lugar das configurações do adapter
    },
    "reminders": { -> fica salvo em algum lugar das configurações do adapter
        "useDefault": boolean -> vai ser false travado,
        "overrides": [
            {
                "method": email -> pode ser ambos,
                "minutes": integer (minimo 0 e maximo 40320) -> pode ser qualquer valor entre 0 e 28 dias, em minutos
            }
            {
                "method": popup -> pode ser ambos,
                "minutes": integer (minimo 0 e maximo 40320) -> pode ser qualquer valor entre 0 e 28 dias, em minutos
            }
        ]
    }
