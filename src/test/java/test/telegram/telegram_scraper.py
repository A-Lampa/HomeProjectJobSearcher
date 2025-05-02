from telethon.sync import TelegramClient
from telethon.tl.functions.messages import GetHistoryRequest
import csv
from datetime import datetime
import pytz

api_id =
api_hash = ''
phone = ''

channels = [
    'https://t.me/evacuatejobs',
    'https://t.me/youritjob',
    'https://t.me/zarubezhom_jobs',
    'https://t.me/qa_jobs',
    'https://t.me/serbia_jobs',
    'https://t.me/haas_vacancies',
    'https://t.me/choicy_work',
    'https://t.me/Relocats',
    'https://t.me/remoteeverafter',
    'https://t.me/relocation_vacancies'
]

keywords = ['qa', 'aqa', 'automation', 'automation qa', 'java']

timezone = pytz.timezone('Europe/Belgrade')
now = datetime.now(timezone)
start_of_day = datetime(now.year, now.month, now.day, 0, 0, 0, tzinfo=timezone)

output_file = 'filtered_vacancies_' + now.strftime('%Y-%m-%d') +  '.csv'

with TelegramClient('anon', api_id, api_hash) as client:
    client.start(phone=phone)

    all_vacancies = []

    for channel in channels:
            try:
                entity = client.get_entity(channel)
                history = client(GetHistoryRequest(
                    peer=entity,
                    limit=200,
                    offset_date=now,
                    offset_id=0,
                    max_id=0,
                    min_id=0,
                    add_offset=0,
                    hash=0
                ))

                for message in history.messages:
                    if message.message:
                        msg_date = message.date.astimezone(timezone)
                        if msg_date >= start_of_day:
                            message_text = message.message.lower()
                            if any(keyword in message_text for keyword in keywords):
                                all_vacancies.append({'channel': channel, 'text': message.message})
            except Exception as e:
                print(f'Error with the channel:  {channel}: {e}')

        # Save in CSV
    with open(output_file, 'w', newline='', encoding='utf-8') as f:
        writer = csv.DictWriter(f, fieldnames=['channel', 'text'], delimiter='|')
        writer.writeheader()
        writer.writerows(all_vacancies)

    print(f'{len(all_vacancies)} have been collected. The results are saved in {output_file}')