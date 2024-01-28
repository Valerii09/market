PGDMP                       |            market    16.1    16.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16479    market    DATABASE     z   CREATE DATABASE market WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE market;
                valerii1    false            �            1259    16626    available_models    TABLE     G  CREATE TABLE public.available_models (
    id bigint NOT NULL,
    product_id bigint,
    name character varying(255),
    serial_number character varying(255),
    color character varying(50),
    size character varying(20),
    price double precision,
    category character varying(50),
    technology character varying(50),
    dustbin_capacity integer,
    modes_count integer,
    doors_count integer,
    compressor_type character varying(50),
    memory character varying(50),
    chambers_count integer,
    processor_type character varying(50),
    availability boolean
);
 $   DROP TABLE public.available_models;
       public         heap    valerii1    false            �            1259    16625    available_models_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_models_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.available_models_id_seq;
       public          valerii1    false    220            �           0    0    available_models_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.available_models_id_seq OWNED BY public.available_models.id;
          public          valerii1    false    219            �            1259    16481    category    TABLE     c   CREATE TABLE public.category (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.category;
       public         heap    valerii1    false            �            1259    16480    category_id_seq    SEQUENCE     �   CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.category_id_seq;
       public          valerii1    false    216            �           0    0    category_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;
          public          valerii1    false    215            �            1259    16488    product    TABLE       CREATE TABLE public.product (
    id bigint NOT NULL,
    manufacturer_name character varying(255),
    manufacturer_country character varying(255),
    online_order_availability boolean,
    installment_option boolean,
    name character varying(255),
    category_id bigint
);
    DROP TABLE public.product;
       public         heap    valerii1    false            �            1259    16514    product_id_seq    SEQUENCE     w   CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          valerii1    false    217            �           0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
          public          valerii1    false    218            &           2604    16647    available_models id    DEFAULT     z   ALTER TABLE ONLY public.available_models ALTER COLUMN id SET DEFAULT nextval('public.available_models_id_seq'::regclass);
 B   ALTER TABLE public.available_models ALTER COLUMN id DROP DEFAULT;
       public          valerii1    false    220    219    220            $           2604    16502    category id    DEFAULT     j   ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);
 :   ALTER TABLE public.category ALTER COLUMN id DROP DEFAULT;
       public          valerii1    false    216    215    216            %           2604    16515 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          valerii1    false    218    217            �          0    16626    available_models 
   TABLE DATA           �   COPY public.available_models (id, product_id, name, serial_number, color, size, price, category, technology, dustbin_capacity, modes_count, doors_count, compressor_type, memory, chambers_count, processor_type, availability) FROM stdin;
    public          valerii1    false    220   #       �          0    16481    category 
   TABLE DATA           ,   COPY public.category (id, name) FROM stdin;
    public          valerii1    false    216   D#       �          0    16488    product 
   TABLE DATA           �   COPY public.product (id, manufacturer_name, manufacturer_country, online_order_availability, installment_option, name, category_id) FROM stdin;
    public          valerii1    false    217   �#       �           0    0    available_models_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.available_models_id_seq', 1, false);
          public          valerii1    false    219            �           0    0    category_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.category_id_seq', 7, true);
          public          valerii1    false    215            �           0    0    product_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.product_id_seq', 117, true);
          public          valerii1    false    218            ,           2606    16649 &   available_models available_models_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.available_models
    ADD CONSTRAINT available_models_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.available_models DROP CONSTRAINT available_models_pkey;
       public            valerii1    false    220            (           2606    16504    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            valerii1    false    216            *           2606    16599    product uk_product_id 
   CONSTRAINT     N   ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_product_id UNIQUE (id);
 ?   ALTER TABLE ONLY public.product DROP CONSTRAINT uk_product_id;
       public            valerii1    false    217            /           2606    16663 1   available_models available_models_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_models
    ADD CONSTRAINT available_models_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);
 [   ALTER TABLE ONLY public.available_models DROP CONSTRAINT available_models_product_id_fkey;
       public          valerii1    false    217    220    4650            -           2606    16581 #   product fk1mtsbur82frn64de7balymq9s    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);
 M   ALTER TABLE ONLY public.product DROP CONSTRAINT fk1mtsbur82frn64de7balymq9s;
       public          valerii1    false    217    216    4648            .           2606    16593    product fk_product_category    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES public.category(id);
 E   ALTER TABLE ONLY public.product DROP CONSTRAINT fk_product_category;
       public          valerii1    false    216    217    4648            0           2606    16674    available_models fk_product_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_models
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.product(id);
 H   ALTER TABLE ONLY public.available_models DROP CONSTRAINT fk_product_id;
       public          valerii1    false    217    4650    220            �   -   x�3�440⼰�����xӅ�_�w�A��3ƏH����� {      �   l   x���	�`Dϻ��c����E��(�|��#'�a��۩�o&��ȇ����u+Vc��;Ũ�9�g��2�>�2��k�����!8����kP�u��d��I�      �   
  x�}��N�@�ϳO�԰���XA1�ڔh<x��E6�[#oﰍh�1s�����|�P`;�$�6��1�JA
�����W�_q�n�C�qyr琎x*�.^A��.i(k�T�"V\	������p�59liGV��_��׽2e��D=�BJ�[u�\�Z����:.�8��a����Q����d�'C���'2l�c�@��^4R��q������?�k�o��b|9�4.w�F���'����<��w�h�q,^τ����{     